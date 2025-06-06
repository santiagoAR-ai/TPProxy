package Ejercicio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileAccess implements ReadAccess {
    private String ruta;
    private String nombreArchivo;

    public FileAccess(String ruta, String nombre) {
        this.ruta = ruta;
        this.nombreArchivo = nombre;
    }

    @Override
    public String readFile() throws IOException {
        return Files.readString(Paths.get(this.ruta + "/" + this.nombreArchivo));
    }

    public boolean empiezaCon(String letra) {
        if (nombreArchivo.startsWith(letra)) {
            return true;
        }
        return false;
    }
}
