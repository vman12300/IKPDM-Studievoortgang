package com.vedex.vedadpiric.ikpdm_studievoortgang.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by vedadpiric on 28-01-17.
 */

public class Student implements Parcelable {

    private String naam;
    private String email;
    private String student_nummer;

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStudent_nummer() {
        return student_nummer;
    }

    public void setStudent_nummer(String student_nummer) {
        this.student_nummer = student_nummer;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(naam);
        parcel.writeString(email);
        parcel.writeString(student_nummer);

    }
    private Student(Parcel in){
        this.naam = in.readString();
        this.email = in.readString();
        this.student_nummer = in.readString();

    }
    public static final Parcelable.Creator<Student> CREATOR = new Parcelable.Creator<Student>() {

        @Override
        public Student createFromParcel(Parcel source) {
            return new Student(source);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}


