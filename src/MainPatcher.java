import org.objectweb.asm.*;
import java.io.IOException;

public class MainPatcher extends ClassVisitor {

    public MainPatcher(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }

    public static byte[] transform(byte[] b) throws IOException {
        final ClassReader CReader = new ClassReader(b);
        final ClassWriter CWriter = new ClassWriter(CReader, ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        CReader.accept(new MainPatcher(Opcodes.ASM9, CWriter), ClassReader.EXPAND_FRAMES);
        return CWriter.toByteArray();
    }

    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {

        // Patch CheckForPremain
        if (name.equals("CheckForPremain")) {
            MethodVisitor NewMethodVisitor = cv.visitMethod(access, name, descriptor, signature, exceptions);
            return new MethodVisitor(Opcodes.ASM9, NewMethodVisitor) {
                @Override
                public void visitCode() {
                    mv.visitInsn(Opcodes.ICONST_1);
                    mv.visitInsn(Opcodes.IRETURN);
                    mv.visitEnd();
                }
            };
        }
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }
}
