package com.example.databaseTest.DatabaseHandler;
import com.example.databaseTest.DatabaseIdentity.PortFolio;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PortFolioRowMapper  implements RowMapper<PortFolio>{

    @Override
    public PortFolio mapRow(ResultSet resultSet, int i) throws SQLException {
        PortFolio portFolio = new PortFolio();
        portFolio.setTicker(resultSet.getString("ticker"));
        portFolio.setUserid(resultSet.getInt("userid"));
        portFolio.setVolume(resultSet.getInt("volume"));
        return portFolio;
    }
}
