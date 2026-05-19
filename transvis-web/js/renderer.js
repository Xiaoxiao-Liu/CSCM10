const SVG_NS = "http://www.w3.org/2000/svg";

function calculatePoint(versionIndex, lineNumber, scaleValue) {
    const s = scaleValue / 100.0;
    const versionDistance = 150;
    const lineDistance = 25;
    return {
        x: (versionIndex + 1) * versionDistance * s * s,
        y: (lineNumber + 2) * lineDistance * s * s,
    };
}

function svgEl(tag, attrs) {
    const el = document.createElementNS(SVG_NS, tag);
    for (const [k, v] of Object.entries(attrs || {})) {
        el.setAttribute(k, v);
    }
    return el;
}

function render(svg, state) {
    svg.innerHTML = "";

    const versions = state.filteredVersions || [];
    if (!versions.length) return;

    const scale = state.itemScale;
    const s = scale / 100.0;
    const lineIncrement = 3;

    let maxItems = 0;
    for (const v of versions) {
        if (v.items.length > maxItems) maxItems = v.items.length;
    }

    const w = (versions.length + 2) * 150 * s * s + 200;
    const h = (maxItems + 8) * 25 * s * s + 100;
    svg.setAttribute("width", Math.max(w, 800));
    svg.setAttribute("height", Math.max(h, 600));
    svg.setAttribute("viewBox", `0 0 ${Math.max(w, 800)} ${Math.max(h, 600)}`);

    const rectUnitWidth = 4.7 * s * s;
    const rectHeight = Math.max(25 * s * s, 4);

    const linesGroup = svgEl("g", { class: "lines-layer" });
    svg.appendChild(linesGroup);

    const itemsGroup = svgEl("g", { class: "items-layer" });
    svg.appendChild(itemsGroup);

    const tokenPositions = [];

    for (let i = 0; i < versions.length; i++) {
        const version = versions[i];
        tokenPositions[i] = {};

        const titlePt = calculatePoint(i, 0, scale);
        const titleG = svgEl("g");
        titleG.appendChild(svgEl("text", {
            x: titlePt.x, y: titlePt.y,
            class: "version-title",
        }));
        titleG.lastChild.textContent = version.author;
        titleG.appendChild(svgEl("text", {
            x: titlePt.x, y: titlePt.y + 18,
            class: "version-year",
        }));
        titleG.lastChild.textContent = String(version.year);
        itemsGroup.appendChild(titleG);

        for (let j = 0; j < version.items.length; j++) {
            const item = version.items[j];
            const stringPt = calculatePoint(i, j + lineIncrement, scale);
            const rectX = stringPt.x + 5;
            const rectY = stringPt.y - 16;
            const rectW = Math.max(item.value * rectUnitWidth, 2);

            tokenPositions[i][item.token] = {
                x: rectX, y: stringPt.y, rectX, rectY, rectW, rectHeight, j
            };

            const color = item.color;
            const rgba = toRgba(color);

            const g = svgEl("g", { class: "item-row" });
            g.dataset.vi = i;
            g.dataset.li = j;
            g.dataset.token = item.token;

            const hitArea = svgEl("rect", {
                class: "hit-area",
                x: stringPt.x - 80,
                y: rectY - 1,
                width: 80 + rectW + 10,
                height: rectHeight + 2,
            });
            g.appendChild(hitArea);

            const bar = svgEl("rect", {
                class: "token-bar",
                x: rectX, y: rectY,
                width: rectW, height: rectHeight,
                fill: rgba,
            });
            g.appendChild(bar);

            if (state.showText) {
                const text = svgEl("text", {
                    class: "token-text",
                    x: stringPt.x - 3,
                    y: stringPt.y - 2,
                    "text-anchor": "end",
                });
                text.textContent = item.token;
                g.appendChild(text);
            }

            itemsGroup.appendChild(g);
        }
    }

    for (let i = 0; i < versions.length - 1; i++) {
        const version = versions[i];
        if (version.is_base) continue;
        const nextPos = tokenPositions[i + 1];
        if (!nextPos) continue;

        for (let j = 0; j < version.items.length; j++) {
            const item = version.items[j];
            const pos = tokenPositions[i][item.token];
            const npos = nextPos[item.token];
            if (!pos || !npos) continue;

            const color = toRgba(item.color);
            const line = svgEl("line", {
                class: "connect-line",
                x1: pos.rectX + pos.rectW,
                y1: pos.y,
                x2: npos.rectX,
                y2: npos.y,
                stroke: color,
            });
            line.dataset.token = item.token;
            linesGroup.appendChild(line);
        }
    }
}

function applyHighlightClasses(svg, state, highlightedTokens, cssClass) {
    const prefix = cssClass === "highlighted" ? "has-highlight" : "has-hover";
    svg.classList.add(prefix);

    svg.querySelectorAll(".item-row").forEach(g => {
        const token = g.dataset.token;
        const vi = parseInt(g.dataset.vi);
        const version = state.filteredVersions[vi];

        let isHighlighted = false;
        if (highlightedTokens.matched) {
            isHighlighted = highlightedTokens.matched.has(token);
        }
        if (highlightedTokens.baseMatched && version && version.is_base) {
            isHighlighted = isHighlighted || highlightedTokens.baseMatched.has(token);
        }

        if (isHighlighted) {
            g.classList.add(cssClass);
        } else {
            g.classList.remove(cssClass);
        }
    });

    svg.querySelectorAll(".connect-line").forEach(line => {
        const token = line.dataset.token;
        if (highlightedTokens.matched && highlightedTokens.matched.has(token)) {
            line.classList.add(cssClass);
        } else {
            line.classList.remove(cssClass);
        }
    });
}

function clearHighlightClasses(svg, cssClass) {
    const prefix = cssClass === "highlighted" ? "has-highlight" : "has-hover";
    svg.classList.remove(prefix);
    svg.querySelectorAll("." + cssClass).forEach(el => el.classList.remove(cssClass));
}
