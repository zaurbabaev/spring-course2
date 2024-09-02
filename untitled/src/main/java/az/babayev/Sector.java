package az.babayev;

public enum Sector {

    Azerbaycan("AZ"),
    Rus("RU"),
    Ingilis("EN"),
    Alman("DE");

    final String title;

    Sector(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
