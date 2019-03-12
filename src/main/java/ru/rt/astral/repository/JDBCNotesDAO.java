package ru.rt.astral.repository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support
        .AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.stereotype.Repository;
import ru.rt.astral.model.Note;
import ru.rt.astral.model.User;

@Repository(value = "jdbcNotesDAO")
public class JDBCNotesDAO extends AbstractNotesDAO{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Note note){
        String query = ""
                + "insert into notes "
                + "(message,author,image) "
                + "values (?,?,?)";
        String source = ""
                + "https://avatars.dicebear.com/v2/"
                + "identicon/"
                + Math.random()
                + ".svg";
        jdbcTemplate.execute(
                query,
                new AbstractLobCreatingPreparedStatementCallback(
                        new DefaultLobHandler()) {
            @Override
            protected void setValues(
                    PreparedStatement ps, 
                    LobCreator lobCreator) 
                    throws SQLException, DataAccessException {
                ps.setString(1, note.getMessage());
                ps.setLong(2, note.getAuthor().getId());
                lobCreator.setBlobAsBytes(ps, 3, recoverImageFromUrl(source));
            }
        });
    }

    @Override
    public void delete(long id) { 
        jdbcTemplate.execute(""
                + "delete from notes "
                + "where id = "
                + id
                + ";");
    }

    @Override
    public Note findOne(long id) {
        List<Note> notes = jdbcTemplate.query(
                "select "
                        + "id,"
                        + "message,"
                        + "author,"
                        + "image"
                        + " from notes where id = " 
                        + id 
                        + ";", 
                (ResultSet rs, int rowNum) -> {
            Note note = new Note();
            note.setId(rs.getLong("id"));
            note.setMessage(rs.getString("message"));
            note.setAuthor(new User());
            note.getAuthor().setId(rs.getLong("author"));
            note.setImage(rs.getBytes("image"));
            System.out.println(note.getImage().length);
            return note;
        });
        if (notes.isEmpty())
            return null;
        return notes.get(0);
    }

    @Override
    public byte[] getNoteImage(long id) {
         List<Note> notes = jdbcTemplate.query(
                "select "
                        + "image"
                        + " from notes where id = " 
                        + id 
                        + ";", 
                (ResultSet rs, int rowNum) -> {
            Note note = new Note();
            note.setImage(rs.getBytes("image"));
            return note;
        });
         try {
            return notes.get(0).getImage();
        } catch (Exception e) {
            return null;
        }
    }
    
    private byte[] recoverImageFromUrl(String source){
        URL url;
        try {
            url = new URL(source);
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            return null;
        }
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try (InputStream inputStream = url.openStream()) {
            int n = 0;
            byte [] buffer = new byte[ 1024 ];
            while (-1 != (n = inputStream.read(buffer))) {
                output.write(buffer, 0, n);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        byte[] result = output.toByteArray();
        System.out.println(result.length);
        return output.toByteArray();
    }

    @Override
    public List<Note> findAllByUserLikeMessage(User user, String message) {
        return jdbcTemplate.query(
                ""
                        + "select * from notes "
                        + "where author = "
                        + user.getId()
                        + " and "
                        + "message like '%"
                        + message
                        + "%'"
                        + ";", 
                (ResultSet rs, int rowNum) -> {
            Note note = new Note();
            note.setId(rs.getLong("id"));
            note.setMessage(rs.getString("message"));
            note.setAuthor(user);
            return note;
        });
    }
    
}
