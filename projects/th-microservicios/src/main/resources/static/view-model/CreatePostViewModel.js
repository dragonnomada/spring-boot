class CreatePostViewModel {
	
	constructor(parent) {
		
		this.parent = parent
		this.postInput = parent.querySelector("input")
		this.postButton = parent.querySelector("button")
		
		console.log(parent)
		console.log(this.postInput)
		console.log(this.postButton)
		
		this.postButton.addEventListener("click", () => this.createPost())
		
		return this
		
	}
	
	show() {
		this.parent.hidden = false
		
		return this
	}
	
	hide() {
		this.parent.hidden = true
		
		return this
	}
	
	getAuthorName() {
		this.authorName = prompt("¿Cuál es tu nombre?")
		
		if (this.authorName) {
			return this
		}
		
		return this.getAuthorName
	}
	
	async createPost() {
		console.log(this)
		
		// TODO: Consumir el API para crear el post		
		const formData = new FormData()
		
		formData.append("content", this.postInput.value || "NADA")
		formData.append("authorName", this.authorName || "UNKNOWN")
		
		const response = await fetch('/api/blog/posts/new', {
			method: 'put',
			body: formData,
			/*headers: {
				"Content-Type": "application/json"
			},
			body: JSON.stringify({ 
				content: this.postInput.value,
				authorName: this.authorName
			})¨*/
		})
		
		if (response.status !== 200) {
			const error = await response.text()
			console.warn(error)
			alert(error)
			return
		}
		
		const post = await response.json()
		
		if (post) {
			//alert("El post fue publicado")
			window.listPostsViewModel.addPost(post)
		} else {
			alert("El post no pudo ser creado")
		}
	}
	
}