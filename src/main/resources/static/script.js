function calculate_size(foot_length) {
    const sizes = {223.0: 35, 230.0: 36, 237.0: 37, 243.0: 38, 250.0: 39, 257.0: 40, 263.0: 41, 270.0: 42, 277.0: 43, 283.0: 44, 290.0: 45, 297.0: 46, 303.0: 47};
    let shoe_size = null;
    const closest_length = Math.min(...Object.keys(sizes).filter(k => k >= foot_length));
    shoe_size = sizes[closest_length];
    return shoe_size;
}

function out() {
    const foot_length = parseFloat(document.getElementById("foot-length").value);
    const foot_size = calculate_size(foot_length);
    const result = document.getElementById("output");
    result.innerHTML = `${foot_length} - это ${foot_size} размер обуви`;
}
