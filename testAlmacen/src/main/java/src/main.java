/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.Scanner;

/**
 *
 * @author Royer
 */
public class main {

    public static void main(String[] args) {
        boolean next = true;
        int opc = 0;
        Scanner read = new Scanner(System.in);
        almacen op = new almacen();
        do {
            System.out.println("######  Menu  #######");
            System.out.println("1 Registrar proveedor (y productos) ");
            System.out.println("2 Listar productos de un determinado proveedor");
            System.out.println("3 Realizar la compra de un determinado producto y actualizar el stock ");
            System.out.println("4 Listar Proveedores ");

            System.out.println("---files---");
            System.out.println("5 Crear archivo");
            //  System.out.println("7 Guardar los registros");

            System.out.println("6 Exit");
            System.out.println("Digite one opcion");
            opc = read.nextInt();

            switch (opc) {

                case 1:
                    op.leerObjetos();
                    op.create();
                    op.guardarObjetos();
                    break;
                case 2:
                    op.leerObjetos();
                    op.searchSupplier();
                    break;
                case 3:
                    op.leerObjetos();
                    op.buyProducts();
                    op.guardarObjetos();
                    break;
                case 4:
                   op.leerObjetos();
                   op.listNameSupplier();
                    break;
                 case 5:
                       op.crearArchivo();
                    break;
               /*  case 6:
                    op.crearArchivo();
                    break;
                case 7:
                    op.guardarObjetos();
                    break;*/
                default:
                    next = false;
                    break;
            }
        } while (next);

    }
}
