package Ejercicio2;

import java.io.IOException;

public class ProxySeguridad implements ReadAccess {
    private Usuario usuario;
    private FileAccess file;

    public ProxySeguridad(Usuario usuario, FileAccess file) {
        this.usuario = usuario;
        this.file = file;
    }

    @Override
    public String readFile() throws IOException {
        if (file.empiezaCon("i") && usuario.poseePermiso(Permiso.ADMIN)) {
            return file.readFile();
        }
        if (file.empiezaCon("m") &&
                (usuario.poseePermiso(Permiso.ADMIN) || usuario.poseePermiso(Permiso.INTERMEDIO))) {
            return file.readFile();
        }
        throw new RuntimeException("No tiene los permisos");
    }
}
