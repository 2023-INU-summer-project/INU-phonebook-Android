package com.example.inuphonebook.LocalDB

import android.content.Context

class RoomRepository(context : Context) {
    private var roomDB : RoomDB
    private var roomDao : RoomDao

    init{
        roomDB = RoomDB.getInstance(context)
        roomDao = roomDB.RoomDao()
    }
    fun getFavEmployee() : List<Employee>{
        return roomDao.getAllEmployee()
    }
    fun insertEmployee(employee : Employee){
        roomDao.insertEmployee(employee)
    }
    fun deleteEmployee(id : Int){
        roomDao.deleteEmployee(id)
    }
    fun updateEmployee(id : Int, isFavorite : Boolean){
        roomDao.updateEmployee(id, isFavorite)
    }

    companion object{
        private var INSTANCE : RoomRepository ?= null

        fun get(context : Context) : RoomRepository{
            return INSTANCE ?:
            RoomRepository(context)
        }
    }
}