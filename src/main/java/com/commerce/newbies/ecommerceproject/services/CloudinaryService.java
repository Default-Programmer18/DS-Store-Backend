package com.commerce.newbies.ecommerceproject.services;

	import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

	

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;
	import org.springframework.web.multipart.MultipartFile;

	import com.cloudinary.Cloudinary;
import com.cloudinary.EagerTransformation;
import com.cloudinary.Transformation;



	
	

	@Service
	public class CloudinaryService  {
		
		
		@Autowired
		private Cloudinary cloudinary;
		
		public Map upload(MultipartFile file) throws Error, IOException
		{
//			if(file.getSize()>5000000)
//			{
				return this.cloudinary.uploader().upload(file.getBytes(), Map.of("folder","EcommerceProject","use_filename",true,
						"eager", Arrays.asList(
						        new EagerTransformation().width(200).height(200).angle(20).crop("crop"))));
//			}
			
//			return this.cloudinary.uploader().upload(file.getBytes(), Map.of("folder","EcommerceProject","use_filename",true));
			
		}
		
		}

	

	
	

