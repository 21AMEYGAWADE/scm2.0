console.log("Script Loaded");

const html = document.querySelector("html");
const themeButton = document.getElementById("theme_change_button");

// apply saved theme
applyTheme(getTheme());

// click event (ONLY ONCE)
themeButton.addEventListener("click", () => {
    let newTheme = html.classList.contains("dark") ? "light" : "dark";
    applyTheme(newTheme);
    setTheme(newTheme);
});

function applyTheme(theme) {
    if (theme === "dark") {
        html.classList.add("dark");
    } else {
        html.classList.remove("dark");
    }
}

function setTheme(theme){
    localStorage.setItem("theme", theme);
}

function getTheme(){
    return localStorage.getItem("theme") || "light";
}