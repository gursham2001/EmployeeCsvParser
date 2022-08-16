package com.sparta.om.DB.model;

public interface SQLQueries {

    public static final String SELECT_ALL = "SELECT * FROM public.user_db";

    String INSERT_INTO_DB = "INSERT INTO public.user_db (id, name, email) Values (?,?,?)";

}
