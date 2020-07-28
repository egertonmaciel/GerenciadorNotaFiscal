package br.com.egerton.connection;

import br.com.egerton.dto.RetornoSQL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Egerton Maciel
 */
public abstract class Conexao {

    protected String URL;
    protected String USUARIO;
    protected String SENHA;

    public Conexao() {
    }

    public RetornoSQL select(String sql) {
        return execute(false, sql, null);
    }

    public RetornoSQL select(String sql, Object parametro) {
        return execute(false, sql, Arrays.asList(new Object[]{parametro}));
    }

    public RetornoSQL select(String sql, List<Object> parametros) {
        return execute(false, sql, parametros);
    }

    public RetornoSQL update(String sql) {
        return execute(true, sql, null);
    }

    public RetornoSQL update(String sql, Object parametro) {
        return execute(true, sql, Arrays.asList(new Object[]{parametro}));
    }
   
    public RetornoSQL update(String sql, List<Object> parametros) {
        return execute(true, sql, parametros);
    }
  
    private RetornoSQL execute(boolean update, String sql, List<Object> parametros) {
        RetornoSQL retorno = new RetornoSQL();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<ArrayList<String>> array = new ArrayList<>();

        try {
            con = (Connection) DriverManager.getConnection(URL, USUARIO, SENHA);
            ps = (PreparedStatement) con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            if (parametros != null) {
                int i = 1;
                for (Object p : parametros) {
                    if (p instanceof String) {
                        ps.setString(i++, (String) p);
                    } else if (p instanceof Date) {
                        ps.setTimestamp(i++, new Timestamp(((Date) p).getTime()));
                    } else if (p instanceof Integer) {
                        ps.setInt(i++, (Integer) p);
                    } else if (p instanceof Long) {
                        ps.setLong(i++, (Long) p);
                    } else if (p instanceof Double) {
                        ps.setDouble(i++, (Double) p);
                    } else if (p instanceof Float) {
                        ps.setFloat(i++, (Float) p);
                    } else if (p instanceof Boolean) {
                        ps.setBoolean(i++, (Boolean) p);
                    } else {
                        ps.setNull(i++, Types.INTEGER);
                    }
                }
            }

            if (update) {
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
            } else {
                rs = ps.executeQuery();
            }

            if (rs.getMetaData() != null) {
                for (int y = 1; y <= rs.getMetaData().getColumnCount(); y++) {
                    retorno.addRotulo(rs.getMetaData().getColumnLabel(y));
                }
            }

            while (rs.next()) {
                ArrayList<String> registro = new ArrayList<>();
                for (int y = 1; y <= rs.getMetaData().getColumnCount(); y++) {
                    registro.add(rs.getString(y));
                }
                array.add(registro);
            }

            rs.close();
            ps.close();
            con.close();
            retorno.setSucesso(true);
            retorno.setValores(array);
            retorno.setMensagem("Sucesso.");
        } catch (Exception e) {
            retorno.setSucesso(false);
            retorno.setMensagem(e.toString());
            try {
                ps.close();
                con.close();
                rs.close();
            } catch (Exception ex) {
            }
        }
        return retorno;
    }

}
