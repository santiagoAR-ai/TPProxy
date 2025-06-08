package Ejercicio2;

import java.io.IOException;
import java.util.List;

public class MainProxy {
    public static void main(String args[]) throws IOException {
        var proxy1 = new ProxySeguridad(new Usuario("santiago", List.of(Permiso.ADMIN)),
                new FileAccess("C:\\Users\\santi\\OneDrive\\Escritorio\\java", "iproxy.txt"));
        System.out.println(proxy1.readFile());
        var proxy2 = new ProxySeguridad(new Usuario("santiago", List.of(Permiso.INTERMEDIO)),
                new FileAccess("C:\\Users\\santi\\OneDrive\\Escritorio\\java", "mproxy.txt"));
        System.out.println(proxy2.readFile());
    }
}
