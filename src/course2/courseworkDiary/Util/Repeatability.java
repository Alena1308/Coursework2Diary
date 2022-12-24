package course2.courseworkDiary.Util;

public enum Repeatability {
    ONETIME("однократная"),
    DAILY("ежедневная"),
    WEEKLY("еженедельная"),
    MONTHLY("ежемесячная"),
    ANNUAL("ежегодная");
    private final String reply;

    Repeatability(String reply) {
        this.reply = validateStringValue(reply);
    }

    @Override
    public String toString() {
        return reply;
    }


    public String validateStringValue(String value) {
        if (value == null || value.isEmpty() || value.isBlank()) {
            return "Укажите тип повторяемости";
        } else {
            return value;
        }
    }

    public final String getReply() {
        return reply;
    }
}
