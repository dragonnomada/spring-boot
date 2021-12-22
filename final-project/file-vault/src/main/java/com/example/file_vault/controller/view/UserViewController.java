package com.example.file_vault.controller.view;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.file_vault.model.File;
import com.example.file_vault.model.User;
import com.example.file_vault.repository.FileRepository;
import com.example.file_vault.security.model.SecurityUser;
import com.example.file_vault.security.service.SecurityService;
import com.example.file_vault.storage.StorageService;

@Controller
@RequestMapping("/public")
public class UserViewController {

	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private StorageService storageService;
	
	@Autowired
	private FileRepository fileRepository;
	
	@GetMapping("/home")
	public String getHome(@RequestParam(required = false) String token, Model model) {
		System.out.printf("Token: %s", token);
		
		if (token == null || token.equals("")) {
			return String.format("redirect:/public/login?error=%s", "La sesión no es válida o ya expiró");
		}
		
		SecurityUser securityUser = securityService.getUserByToken(token);
		
		User user = securityUser.getUser();
		
		model.addAttribute("token", token);
		model.addAttribute("user", user);
		model.addAttribute("files", user.getFiles());
		
		return "UserHome";
	}
	
	@GetMapping("/login")
	public String getLoginForm(Model model) {
		SecurityUser user = SecurityUser.builder().build();
		
		model.addAttribute("securityUser", user);
		
		return "UserLogin";
	}
	
	@PostMapping("/login")
	public String signIn(@ModelAttribute SecurityUser userModel) {
		if (securityService.isValidUser(userModel.getUsername(), userModel.getPassword())) {			
			SecurityUser user = securityService.getUser(userModel.getUsername());
			
			String token = securityService.generateToken(user);
			
			return String.format("redirect:/public/home?token=%s", token);
		}
		
		return String.format("redirect:/public/login?error=%s", "Invalid username or password");
	}
	
	@GetMapping("/file/{id}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable long id) {

		Optional<File> fileOptional = fileRepository.findById(id);
		
		File fileEntity = fileOptional.get();
		
		Resource file = storageService.loadAsResource(fileEntity.getPath());
		return ResponseEntity.ok().header("Content-Disposition",
				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
	
	@PostMapping("/upload")
	public String postFileUpload(@RequestParam(required = true) String token, @RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {
		System.out.printf("Token: %s", token);
		
		if (token == null || token.equals("")) {
			return String.format("redirect:/public/login?error=%s", "La sesión no es válida o ya expiró");
		}
		
		SecurityUser securityUser = securityService.getUserByToken(token);
		
		User user = securityUser.getUser();

		storageService.store(user, file);
		
		System.out.println("Subiendo archivo:");
		System.out.println(user.getUsername());
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getContentType());
		System.out.println(file.getSize());
		
		redirectAttributes.addFlashAttribute("message",
				"Se ha subido el archivo " + file.getOriginalFilename() + "!");
		redirectAttributes.addAttribute("token", token);

		return "redirect:/public/home";
	}
	
	
}
