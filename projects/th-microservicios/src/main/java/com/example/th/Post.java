package com.example.th;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

	@Getter @Setter
	private Long id;
	
	@Getter @Setter
	private String content;
	
	@Getter @Setter
	private Date createAt;
	
	@Getter @Setter
	private String authorName;
	
}
