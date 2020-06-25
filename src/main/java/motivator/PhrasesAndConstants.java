
package motivator;

public class PhrasesAndConstants {

    private PhrasesAndConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String CARD_TITLE = "Der Motivator";

    public static final String ACTIVITY_SLOT = "Activity";
    public static final String ACTIVITY_NEW_SLOT = "ActivityNew";
    public static final String NUMBER_SLOT = "Number";
    public static final String PLACE_SLOT = "Place";

    public static final String WELCOME = "Hallo. Ich bin der Motivator. Ich lerne deine Lieblingshobbies. Bitte sage mir zum Beispiel: Ich mag Eis essen.";
    public static final String HELP = "Du kannst mir Dein Lieblingshobby sagen. Sage mir zum Beispiel: Ich mag schwimmen.";
    public static final String HELP_REPROMPT = "Bitte sage mir nochmal Dein Lieblingshobby.";
    public static final String FALLBACK = "Tut mir leid, das weiss ich nicht. Sage einfach Hilfe.";
    public static final String CANCEL_AND_STOP = "Auf Wiedersehen";
    public static final String NO_HOBBY_BY_THAT_NAME = "Das Hobby ist noch nicht unter deinen Hobbies gespeichert. Bitte speichere es erst!";

    // AddActivity
    public static final String CARDTEXT_ADDACTIVITY = "Hobby hinzufügen";
    public static final String ADD_HOBBY_FIRST = "Bitte füge erst ein Hobby hinzu!";
    public static final String ALREADY_EXISTS = "Dieses Hobby gibt es bereits.";
    public static final String ACTIVITY_ADDED = "Ich habe %s zu deinen Hobbies hinzugefügt";

    // GetAllActivities
    public static final String CARDTEXT_GETALLACTIVITIES = "Alle Hobbies ausgeben";
    public static final String NO_ACTIVITIES_YET = "Du hast noch keine Hobbies abgespeichert";
    public static final String ACTIVITIES_OUTPUT = "Du hast bis jetzt %s abgespeichert.";

    // ConfigureActivity
    public static final String CARDTEXT_CONFIGUREACTIVITY = "Hobby ändern";
    public static final String CHANGE_SUCCESSFULL = "Du hast erfolgreich das Hobby %s geändert.";

    // AskForActivity
    public static final String CARDTEXT_ASKFORACTIVITY = "Vorgeschlagenes Hobby";
    public static final String PROPOSED_HOBBY = "Wie wäre es mit %s?";

    // DeleteActivity
    public static final String CARDTEXT_DELETEACTIVITY = "Löschen eines Hobbies";
    public static final String DELETED_SUCCESSFULL = "Das Hobby %s wurde erfolgreich gelöscht.";
    public static final String DELETED_ALL_SUCCESSFULL = "Alle Hobbies wurden erfolgreich gelöscht.";

    // HobbyAmount
    public static final String CARDTEXT_HOBBYAMOUNT = "Menge der Hobbies";
    public static final String AMOUNT_SAVED = "Du hast %d Hobbies abgespeichert";

    // Information
    public static final String CARDTEXT_INFORMATION = "Information zu einem Hobby";
    public static final String INFORMATION_NAME = "Der Name des Hobbies ist %s ";
    public static final String INFORMATION_DURATION = "es dauert %d Minuten ";
    public static final String INFORMATION_PLACE = "und findet %s statt.";
}
