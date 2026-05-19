function calculateColor(frequency, alpha) {
    const r = (Math.sin(0.22 * frequency + 0) * 127 + 128) / 255;
    const g = (Math.sin(0.22 * frequency + 2) * 127 + 128) / 255;
    const b = (Math.sin(0.22 * frequency + 4) * 127 + 128) / 255;
    return { r, g, b, a: alpha };
}

function toRgba(color) {
    return `rgba(${Math.round(color.r * 255)}, ${Math.round(color.g * 255)}, ${Math.round(color.b * 255)}, ${color.a})`;
}

function colorWithAlpha(color, alpha) {
    return { r: color.r, g: color.g, b: color.b, a: alpha };
}
