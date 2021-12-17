class ListPostsViewModel {
	
	constructor(parent) {
		console.log("ListPostsViewModel", parent)
		
		this.parent = parent
		
		return this
	}
	
	async addPost(post) {
		console.log("Enviando post", this.parent, post)
		
		const postView = document.createElement("div")
		
		postView.innerHTML = `
			<p>${post.content}</p>
			<p>
				<span><strong>${post.authorName}</strong></span> 
				<span><small>${post.createAt}</small></span>
			</p>
		`
		
		this.parent.appendChild(postView)
		
		return this
	}
	
}