package co.com.grupoasd.nomina.negocio.tiposdocumento;

import co.com.grupoasd.nomina.dao.TipoDocumentoDao;
import co.com.grupoasd.nomina.modelo.TipoDocumento;
import java.util.List;

public class TiposDocumentoController implements ITiposDocumento{

    @Override
    public List<TipoDocumento> listarTiposDocumento() {
        TipoDocumentoDao dao = new TipoDocumentoDao();
        return dao.listar();
    }

    @Override
    public List<TipoDocumento> listarTiposDocumentoCargo() {
        TipoDocumentoDao dao = new TipoDocumentoDao();
        return dao.listarValidoCargo();
    }
}
