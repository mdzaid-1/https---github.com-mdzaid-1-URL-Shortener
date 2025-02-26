document.getElementById("urlForm").addEventListener("submit", function(event) {
    event.preventDefault();
    
    const originalUrl = document.getElementById("originalUrl").value;
    
    fetch("http://localhost:8082/api/shorten", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ originalUrl: originalUrl })
    })
    .then(response => response.json())
    .then(data => {
        localStorage.setItem("shortenedUrl", data.shortenedUrl);
        document.getElementById("viewShortUrl").classList.remove("hidden");
    })
    .catch(error => console.error("Error:", error));
});

document.getElementById("viewShortUrl").addEventListener("click", function() {
    window.location.href = "output.html";
});
