package com.haihong.recyclenestdemo;

import java.io.Serializable;
import java.util.List;

/**
 */
public class Bean implements Serializable {
    private String message;
    private List<DatasBean> datas;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean implements Serializable {
        private String title;
        private List<Option> options;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<Option> getOptions() {
            return options;
        }

        public void setOptions(List<Option> options) {
            this.options = options;
        }

        public static class Option implements Serializable {
            private String datas;
            private boolean select;

            public String getDatas() {
                return datas;
            }

            public void setDatas(String datas) {
                this.datas = datas;
            }

            public boolean isSelect() {
                return select;
            }

            public void setSelect(boolean select) {
                this.select = select;
            }
        }

    }

}
