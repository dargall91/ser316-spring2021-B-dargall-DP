package codeamon;

public class CodeamonFactory {
    public static Codeamon getCodeamon(Type type, int level) {
        if (type == Type.Fire) {
            return new FireCodeamon(level);
        }

        return null;
    }
}
