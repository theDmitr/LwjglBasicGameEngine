package dmitr.basic.core.log;

public enum LogType {

    INFO("INFO"), ERROR("ERROR"), WARNING("WARNING");

    private final String caption;

    LogType(String caption) {
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }

}
