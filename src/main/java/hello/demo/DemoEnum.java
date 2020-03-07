package hello.demo;

/**
 * @创建人 sgwang
 * @name DemoEnum
 * @user Anti
 * @创建时间 2020/3/8
 * @描述
 */
public enum DemoEnum {
    First(1, "第一名"),
    Second(2, "第二名"),
    Third(3, "第三名");

    Integer index;
    String desc;

    DemoEnum(Integer index, String desc) {
        this.index = index;
        this.desc = desc;
    }

    /**
     * 根据 传入index 返回对应枚举
     *
     * @param index
     * @return
     */
    public static DemoEnum getEnumByIndex(Integer index) {
        for (DemoEnum temp : values()) {
            if (temp.getIndex() == index) {
                return temp;
            }
        }

        return null;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
