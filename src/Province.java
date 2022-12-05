import java.util.Arrays;

public enum Province{AB, BC, MB, NB, NL, NS, NT, NU, ON, PE, QC, SK, YT;

    public static String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }

}
