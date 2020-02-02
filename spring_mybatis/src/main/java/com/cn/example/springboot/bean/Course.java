package com.cn.example.springboot.bean;

public class Course {
    private int courseId;
    private String courseName;
    private String description_Zh;
    private String description_En;


    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription_Zh() {
        return description_Zh;
    }

    public void setDescription_Zh(String description_Zh) {
        this.description_Zh = description_Zh;
    }

    public String getDescription_En() {
        return description_En;
    }

    public void setDescription_En(String description_En) {
        this.description_En = description_En;
    }
}
