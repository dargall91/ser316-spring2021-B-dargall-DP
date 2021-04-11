package codeamon;

public class StatsFactory {
    public static Stats getStats(Type type, int level) {
        if (type == Type.Fire) {
            return new FireStats(level);
        }

        return null;
    }
}
