import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class Transformer implements ClassFileTransformer {

    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,  ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        try
        {
            // If the Class is in a package you check that like that:
            // if (className.equals("my/package/Main"))
            if (className.equals("Main"))
                return MainPatcher.transform(classfileBuffer);
        }
        catch (Exception e)
        {
            System.out.println("Error while Patching Classes");
        }
        return null;
    }

}