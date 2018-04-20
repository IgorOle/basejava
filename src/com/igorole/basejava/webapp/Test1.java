package com.igorole.basejava.webapp;


import com.igorole.basejava.webapp.model.Resume;
import com.igorole.basejava.webapp.storage.SqlStorage;
import org.postgresql.util.ReaderInputStream;

public class Test1 {

    public static void main(String[] args) {
//        SqlHelper sqlHelper = new SqlHelper(() -> DriverManager.getConnection("jdbc:postgresql://localhost:5432/resumes", "postgres", "111"));
//        sqlHelper.execute("delete from resume where uuid=?", (PreparedStatement ps) -> {
//            ps.setString(1, "1");
//            ps.execute();
//        } );
        Resume r = new Resume("1", "full name11");
        Resume r1;

        SqlStorage sqlStorage = new SqlStorage("jdbc:postgresql://localhost:5432/resumes", "postgres", "111");
        sqlStorage.clear();
        sqlStorage.save(r);
        //sqlStorage.delete("1");
        r1 = sqlStorage.get("1");
        System.out.println(r1.getFullName());
    }
}
