/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Royer
 */
public class almacen {

    private List<proveedor> supplier;
    Scanner leer;

    public almacen() {
        supplier = new ArrayList<>();
        leer = new Scanner(System.in);
    }

    public void create() {
        proveedor prov = new proveedor();
        System.out.println("Digite la razon social:");
        prov.setRazonSocial(leer.nextLine());
        System.out.println("Digite la Direccion:");
        prov.setDireccion(leer.nextLine());
        System.out.println("Digite el nro de Telefono");
        prov.setNroTelefono(leer.nextInt());

        //***************************
        //String opc;
        boolean next = true;
        do {
            System.out.println("######  Sub Menu Productos  #######");
            System.out.println("1 Registrar ");
            System.out.println("2 Exit");
            System.out.println("Digite one opcion");
            int opc = leer.nextInt();
            leer.nextLine();

            switch (opc) {
                case 1:
                    producto pro = new producto();
                    System.out.println("Digite el nombre del producto");
                    pro.setNombreProducto(leer.nextLine());
                    System.out.println("Digite el costo unitario");
                    pro.setCostoUnitario(leer.nextFloat());
                    System.out.println("Digite el stock(one number)");
                    pro.setStock(leer.nextInt());
                    //leer.nextLine();ADD LIST SUPPLIER
                    prov.getProducts().add(pro);
                    break;

                default:
                    next = false;
                    break;
            }
        } while (next);
        //**********ADD SUPPLIER AL ALMACEN************
        supplier.add(prov);

    }
    //Listar los datos de todos los productos de un determinado proveedor

    public void searchSupplier() {
        //Buscar un determinado libro por titulo
        System.out.println("DIGITE EL PROVEEDOR:");
        String title = leer.nextLine();
        if (supplier.isEmpty()) {
            System.out.println("No tiene Proveedores!!!!");
        } else {

            proveedor encontrado = searchByName(title);
            if (encontrado == null) {
                System.out.println("NO EXISTE  EL PROVEEDOR " + title);
            } else {
                //LISTAR PRODUCTOS
                System.out.println("--------LISTA PRODUCTOS---------");
                listProducts(encontrado);
            }

        }

    }

    public void buyProducts() {
        System.out.println("Digite el producto que desea comprar :");
        String npr = leer.nextLine();
        System.out.println("Digite las unidades que desea");
        int uni = leer.nextInt();
        if (searchProduct(npr, uni)) {
            System.out.println("Compra exitosa!!!");

            //Disminuir sock
        } else {
            System.out.println("NO existe el producto  !!!");
        }

    }

    public boolean searchProduct(String namep, int can) {
        boolean sw = false;
        find:
        for (proveedor obj : supplier) {
            for (producto pr : obj.getProducts()) {
                if (pr.getNombreProducto().equalsIgnoreCase(namep)) {
                    //System.out.println("Producto encontrado!!!");
                   
                    if (can<=pr.getStock()) {
                        pr.setStock(pr.getStock() - can);
                         sw = true;
                    }else{
                        System.out.println("NO hay stock suficiente o ");
                         
                    }
                    
                    break find;
                }
            }
        }
        return sw;
    }

    public proveedor searchByName(String na) {//para el proveedor
        proveedor pro = null;
        for (proveedor ob : supplier) {
            if (ob.getRazonSocial().equalsIgnoreCase(na)) {
                pro = ob;
                break;
            }
        }
        return pro;

    }

    public void listProducts(proveedor proved) {
        //supplier.forEach((n) -> System.out.println(n));
        for (producto produ : proved.getProducts()) {
            System.out.println("nombre > " + produ.getNombreProducto());
            System.out.println("consto unitario > " + produ.getCostoUnitario());
            System.out.println("stock > " + produ.getStock());
            System.out.println("+++++++++++++++++++++++++++");
        }

    }
    public void listNameSupplier(){
        System.out.println("LISTA DE PROVEEDORES");
        supplier.forEach((n)->System.out.println("*"+n.getRazonSocial()));
    }
    

    /*++++++++++++MANEJO DE ARCHIVOS++++++++++++++++*/
    //CREANDO UN ARCHIVO
    public void crearArchivo() {
        //ya debe estar creado el directorio D:\\programacionIII
        Path path = Paths.get("D:\\programacionIII\\archivoAlmacen.txt");
        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
                System.out.println("El archivo se creo correctamente");
            } else {
                System.out.println("El archivo ya existe");
            }
        } catch (Exception e) {
        }
    }

    //guardando la listacliente dentro del archivo creado con anterioridad
    public void guardarObjetos() {
        String ruta = "D:\\programacionIII\\archivoAlmacen.txt";
        try {
            //****SERIALIZAN(BITS) PARA QUE SE GUARDE DENTRO EL ARCHIVO*****
            FileOutputStream archivo = new FileOutputStream(ruta);//INIALIZAMOS EL ARCHIVO
            ObjectOutputStream oos = new ObjectOutputStream(archivo);//podamos guardar dentro del archivo
            //*******
            oos.writeObject(supplier);
            oos.close();
            archivo.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(almacen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(almacen.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    //cargamos los datos del archivo a un list<Cliente>
    public void leerObjetos() {
        String ruta = "D:\\programacionIII\\archivoAlmacen.txt";
        try {

            FileInputStream archivo = new FileInputStream(ruta);
            ObjectInputStream ois = new ObjectInputStream(archivo);

            if (ois != null) {
                supplier = (List<proveedor>) ois.readObject();//realizamos un casteo
            } else {
                System.out.println("El objeto es nulo");
            }
        } catch (FileNotFoundException e) {
            Logger.getLogger(almacen.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException ex) {
            Logger.getLogger(almacen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<proveedor> getSupplier() {
        return supplier;
    }
    
    

}
