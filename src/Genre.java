import java.util.Arrays;

public enum Genre{FICTION, NONFICTION, SCIFI, FANTASY, MYSTERY, ROMANCE, THRILLER, HISTORY, HORROR,
    ADVENTURE, GRAPHIC, MANGA, POETRY, COOKBOOK, CHILDREN, GUIDEBOOK, RESEARCH, BIOGRAPHIC, INFORMATIONAL;

    public static String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }
}



