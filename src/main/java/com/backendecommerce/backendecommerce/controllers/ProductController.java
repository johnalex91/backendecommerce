package com.backendecommerce.backendecommerce.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import java.net.MalformedURLException;

import com.backendecommerce.backendecommerce.models.entities.Product;
import com.backendecommerce.backendecommerce.services.IProductService;
import com.backendecommerce.backendecommerce.services.IUploadFileService;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RequestMapping("/api/product")
public class ProductController {
    
	@Autowired
	private IProductService service;
	
	@Autowired
	private IUploadFileService uploadService;

    @GetMapping("/")
	@ResponseBody
	public ResponseEntity<?> show() throws Exception{
        Map<String,Object> response = new HashMap<>();
		try {		
            List<Product> data = service.show();
            response.put("data", data);
			
		}catch(NumberFormatException e) {
			System.out.println("Error Number Format "+e.getMessage());
		}
		catch(NullPointerException e) {
			System.out.println("Error nm pointer "+e.getMessage());
		}
	   return new ResponseEntity<Map<String,Object>>(response,HttpStatus.ACCEPTED);
	}

    @GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<?> edit(@PathVariable Long id) throws Exception{
        Map<String,Object> response = new HashMap<>();
		try {		
            Product data = service.findById(id);
            response.put("data", data);
		}catch(NumberFormatException e) {
			System.out.println("Error Number Format "+e.getMessage());
		}
		catch(NullPointerException e) {
			System.out.println("Error nm pointer "+e.getMessage());
		}
	   return new ResponseEntity<Map<String,Object>>(response,HttpStatus.ACCEPTED);
	}    

    @PostMapping("/buy/{id}")
	@ResponseBody
	public ResponseEntity<?> buy(@PathVariable Long id) throws Exception{
        Map<String,Object> response = new HashMap<>();
		try {		
            Product data = service.findById(id);
            data.setQuantity(data.getQuantity()-1);
            service.save(data);
            response.put("data", data);			
		}catch(NumberFormatException e) {
			System.out.println("Error Number Format "+e.getMessage());
		}
		catch(NullPointerException e) {
			System.out.println("Error nm pointer "+e.getMessage());
		}
	   return new ResponseEntity<Map<String,Object>>(response,HttpStatus.ACCEPTED);
	}    

    @PostMapping("/create")
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody Product obj) throws Exception{
        Map<String,Object> response = new HashMap<>();
		try {		
            Product data = null;
            if(obj.getId() == 0){
                data = new Product();;
            }else{
                data = service.findById(obj.getId());

            }
            data.setQuantity(obj.getQuantity());
            data.setCategory(obj.getCategory());
            data.setName(obj.getName());
            data.setReference(obj.getReference());
            service.save(data);
            response.put("data", data);			
		}catch(NumberFormatException e) {
			System.out.println("Error Number Format "+e.getMessage());
		}
		catch(NullPointerException e) {
			System.out.println("Error nm pointer "+e.getMessage());
		}
	   return new ResponseEntity<Map<String,Object>>(response,HttpStatus.ACCEPTED);
	}    
    

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) throws Exception{
        Map<String,Object> response = new HashMap<>();
		try {		
            service.delete(id);
            response.put("data", "Eliminado con exito");			
		}catch(NumberFormatException e) {
			System.out.println("Error Number Format "+e.getMessage());
		}
		catch(NullPointerException e) {
			System.out.println("Error nm pointer "+e.getMessage());
		}
	   return new ResponseEntity<Map<String,Object>>(response,HttpStatus.ACCEPTED);
	}  

	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
		Map<String, Object> response = new HashMap<>();
		
		Product data = service.findById(id);
		
		if(!archivo.isEmpty()) {

			String nombreArchivo = "NONE";
			try {
				nombreArchivo = uploadService.copiar(archivo);

			                
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del cliente");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
                    if(data.getImage()!=null){
                        String nombreFotoAnterior = data.getImage();
                        uploadService.eliminar(nombreFotoAnterior);    
                    }

                    System.out.println("***********************");
                    System.out.println(id);
                    System.out.println("***********************");
                    data.setImage(nombreArchivo);
                    service.save(data);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
			
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}


	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){

		Resource recurso = null;
		
		try {
			recurso = uploadService.cargar(nombreFoto);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}


}
