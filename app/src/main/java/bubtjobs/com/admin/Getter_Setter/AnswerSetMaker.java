package bubtjobs.com.admin.Getter_Setter;

/**
 * Created by Murtuza on 3/15/2016.
 */
public class AnswerSetMaker {
    private String key;
    private String value;

    public AnswerSetMaker() {
    }

    public AnswerSetMaker(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
