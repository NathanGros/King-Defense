package kingdefense.frontend;

import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.IntPointer;

import static com.raylib.Raylib.*;

public class ShadersManager {
    private static Integer SHADOWMAP_RESOLUTION = 4096;
    private Shader shadowShader;
    private RenderTexture shadowMap;
	private Camera3D lightCamera;
	private Vector3 lightDir;
    private int lightVPLoc;
    private int shadowMapLoc;

	public ShadersManager() {
        lightDir = Vector3Normalize(new Vector3().x(-2.f).y(-4.0f).z(-2.f));
    }

    public Shader getShaders() {
        return shadowShader;
    }
    public RenderTexture getShadowMap() {
		return shadowMap;
	}
    public Camera3D getLightCamera() {
		return lightCamera;
	}
    public int getLightVPLoc() {
		return lightVPLoc;
	}
	public int getShadowMapLoc() {
		return shadowMapLoc;
	}

    RenderTexture LoadShadowmapRenderTexture(int width, int height) {
        RenderTexture target = new RenderTexture();
        target.id(rlLoadFramebuffer()); // Load an empty framebuffer
        target.texture().width(width);
        target.texture().height(height);
        if (target.id() > 0) {
            rlEnableFramebuffer(target.id());
            // Create depth texture
            // We don't need a color texture for the shadowmap
            target.depth().id(rlLoadTextureDepth(width, height, false));
            target.depth().width(width);
            target.depth().height(height);
            target.depth().format(19);       //DEPTH_COMPONENT_24BIT?
            target.depth().mipmaps(1);
            // Attach depth texture to FBO
            rlFramebufferAttach(target.id(), target.depth().id(), RL_ATTACHMENT_DEPTH, RL_ATTACHMENT_TEXTURE2D, 0);
            // Check if fbo is complete with attachments (valid)
            if (rlFramebufferComplete(target.id())) 
                System.out.println("FBO: [ID %i] Framebuffer object created successfully" + target.id());
            rlDisableFramebuffer();
        }
        else System.out.println("FBO: Framebuffer object can not be created");
        return target;
    }

    public void initShaders() {
        shadowShader = LoadShader("src/main/java/kingdefense/frontend/shaders/shadows.vs", "src/main/java/kingdefense/frontend/shaders/shadows.fs");
        shadowShader.locs().put(SHADER_LOC_VECTOR_VIEW, GetShaderLocation(shadowShader, "viewPos"));
        Color lightColor = new Color().r((byte) 170).g((byte) 170).b((byte) 170).a((byte) 255);
        Vector4 lightColorNormalized = ColorNormalize(lightColor);
        int lightDirLoc = GetShaderLocation(shadowShader, "lightDir");
        int lightColLoc = GetShaderLocation(shadowShader, "lightColor");
        SetShaderValue(shadowShader, lightDirLoc, lightDir, SHADER_UNIFORM_VEC3);
        SetShaderValue(shadowShader, lightColLoc, lightColorNormalized, SHADER_UNIFORM_VEC4);
        int ambientLoc = GetShaderLocation(shadowShader, "ambient");
        FloatPointer ambientPtr = new FloatPointer(4);
        ambientPtr.put(0, 4.0f);
        ambientPtr.put(1, 4.0f);
        ambientPtr.put(2, 4.0f);
        ambientPtr.put(3, 1.0f);
        SetShaderValue(shadowShader, ambientLoc, ambientPtr, SHADER_UNIFORM_VEC4);
        lightVPLoc = GetShaderLocation(shadowShader, "lightVP");
        shadowMapLoc = GetShaderLocation(shadowShader, "shadowMap");
        IntPointer resPtr = new IntPointer(1);
        resPtr.put(0, SHADOWMAP_RESOLUTION);
        SetShaderValue(shadowShader, GetShaderLocation(shadowShader, "shadowMapResolution"), resPtr, SHADER_UNIFORM_INT);
        shadowMap = LoadShadowmapRenderTexture(SHADOWMAP_RESOLUTION, SHADOWMAP_RESOLUTION);
        Vector3 lightTarget = new Vector3().x(3.5f).y(0).z(3.5f);
        lightCamera = new Camera3D()
            ._position(Vector3Add(lightTarget, Vector3Scale(lightDir, -15.0f)))
            .target(lightTarget)
            .up(new Vector3().x(0).y(1).z(0))
            .fovy(20.0f)
            .projection(CAMERA_ORTHOGRAPHIC);
    }

    public void unloadShaders() {
        UnloadRenderTexture(shadowMap);
        UnloadShader(shadowShader);
    }
}
