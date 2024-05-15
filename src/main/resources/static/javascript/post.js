// Wait for the DOM to be fully loaded
document.addEventListener('DOMContentLoaded', function() {
    // Function to fetch and display all posts
    function fetchPosts() {
        fetch('/api/posts')
            .then(response => response.json())
            .then(posts => {
                // Update the UI with fetched posts
                displayPosts(posts);
            })
            .catch(error => {
                console.error('Error fetching posts:', error);
            });
    }

    // Function to display posts on the page
    function displayPosts(posts) {
        const postsContainer = document.getElementById('posts-container');
        postsContainer.innerHTML = ''; // Clear existing posts

        posts.forEach(post => {
            const postElement = document.createElement('div');
            postElement.innerHTML = `
                <h2>${post.title}</h2>
                <p>${post.content}</p>
                <a href="/post/detail/${post.id}">Read more</a>
            `;
            postsContainer.appendChild(postElement);
        });
    }

    // Function to handle form submission for creating a new post
    function handleCreatePostFormSubmit(event) {
        event.preventDefault(); // Prevent the default form submission behavior

        const formData = new FormData(event.target); // Get form data
        const postData = {
            title: formData.get('title'),
            content: formData.get('content')
        };

        // Send POST request to create a new post
        fetch('/api/posts', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(postData)
        })
        .then(response => response.json())
        .then(createdPost => {
            // Optionally, redirect to the newly created post or refresh the page
            window.location.href = `/post/detail/${createdPost.id}`;
        })
        .catch(error => {
            console.error('Error creating post:', error);
        });
    }

    // Add event listener for the form submit event to create a new post
    const createPostForm = document.getElementById('create-post-form');
    if (createPostForm) {
        createPostForm.addEventListener('submit', handleCreatePostFormSubmit);
    }

    // Fetch and display all posts when the page loads
    fetchPosts();
});
