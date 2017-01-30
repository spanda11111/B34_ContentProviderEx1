package com.example.satya.b34_contentproviderex1;

import android.net.Uri;

import java.net.URI;

/**
 * Created by satya on 1/29/2017.
 */
//URI- Uniform Resource Identifire
//declare column names and prepare Uri for ur tables
public class UriProvider
{
    //column namaes for first table
    public  static  final  String _ID ="_id";
    public static  final  String  NAME = "sname";
    public  static  final  String SUB ="ssub";
    //prepare URI for student information
    public static final Uri STUDENT_URI = Uri.parse("content://com.techpalle.B34/studenttable");
}
