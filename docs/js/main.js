const state = {
    mode: "frequency",
    allVersions: [],
    visibleIndices: [],
    filteredVersions: [],
    showText: true,
    itemScale: 100,
    panelScale: 100,
    colorLegend: [],
    lockedToken: null,
    lockedVersion: null,
};

let svg, tooltip;

function init() {
    svg = document.getElementById("vis-svg");
    tooltip = document.getElementById("tooltip");

    document.querySelectorAll(".mode-btn").forEach(btn => {
        btn.addEventListener("click", () => loadMode(btn.dataset.mode));
    });

    document.getElementById("chk-text-toggle").addEventListener("change", onTextToggle);
    document.getElementById("slider-item-scale").addEventListener("input", onItemScaleChange);
    document.getElementById("slider-panel-scale").addEventListener("input", onPanelScaleChange);
    document.getElementById("sidebar-toggle").addEventListener("click", toggleSidebar);

    svg.addEventListener("mouseover", onSvgHover);
    svg.addEventListener("mouseout", onSvgHoverOut);
    svg.addEventListener("mousemove", onSvgMouseMove);
    svg.addEventListener("click", onSvgClick);

    loadMode("frequency");
}

/* ===== Data Loading ===== */
function loadMode(mode) {
    state.mode = mode;
    state.lockedToken = null;
    state.lockedVersion = null;

    document.querySelectorAll(".mode-btn").forEach(btn => btn.classList.remove("active"));
    document.querySelector(`.mode-btn[data-mode="${mode}"]`).classList.add("active");

    const modeNames = {
        frequency: "Frequency", tfidf: "TF-IDF",
        lemma_frequency: "Lemma + Freq", lemma_tfidf: "Lemma + TF-IDF",
    };
    document.getElementById("mode-indicator").textContent = modeNames[mode] || mode;

    showLoading(true);

    fetch("./data/" + mode + ".json")
        .then(res => res.json())
        .then(data => {
            state.allVersions = data.versions;
            state.colorLegend = data.color_legend;
            initVersionCheckboxes();
            selectAllVersions();
            buildColorLegend();
            redraw();
            showLoading(false);
        });
}

function showLoading(show) {
    document.getElementById("loading-overlay").classList.toggle("hidden", !show);
}

/* ===== Version Checkboxes ===== */
function initVersionCheckboxes() {
    const container = document.getElementById("version-checkboxes");
    container.innerHTML = "";

    const allLabel = document.createElement("label");
    allLabel.className = "version-label all-label";
    const allCb = document.createElement("input");
    allCb.type = "checkbox";
    allCb.checked = true;
    allCb.className = "all-cb";
    allCb.addEventListener("change", e => {
        container.querySelectorAll('input[type="checkbox"]:not(.all-cb)').forEach(cb => {
            cb.checked = e.target.checked;
        });
        updateVisibleVersions();
    });
    allLabel.appendChild(allCb);
    allLabel.appendChild(document.createTextNode("Select All"));
    container.appendChild(allLabel);

    state.allVersions.forEach((v, idx) => {
        const label = document.createElement("label");
        label.className = "version-label";
        const cb = document.createElement("input");
        cb.type = "checkbox";
        cb.checked = true;
        cb.dataset.index = idx;
        cb.addEventListener("change", updateVisibleVersions);
        label.appendChild(cb);
        const displayName = v.year + " " + v.author;
        label.appendChild(document.createTextNode(displayName));
        container.appendChild(label);
    });
}

function selectAllVersions() {
    state.visibleIndices = state.allVersions.map((_, i) => i);
    updateFilteredVersions();
}

function updateVisibleVersions() {
    const container = document.getElementById("version-checkboxes");
    const cbs = container.querySelectorAll('input[type="checkbox"]:not(.all-cb)');
    state.visibleIndices = [];
    cbs.forEach(cb => {
        if (cb.checked) state.visibleIndices.push(parseInt(cb.dataset.index));
    });
    updateFilteredVersions();
    state.lockedToken = null;
    redraw();
}

function updateFilteredVersions() {
    state.filteredVersions = state.visibleIndices.map(i => state.allVersions[i]);
}

/* ===== Color Legend ===== */
function buildColorLegend() {
    const container = document.getElementById("color-legend");
    container.innerHTML = "";

    state.colorLegend.slice(0, 80).forEach(entry => {
        const div = document.createElement("div");
        div.className = "legend-entry";
        div.dataset.value = entry.value;
        div.addEventListener("click", () => {
            document.querySelectorAll(".legend-entry.active").forEach(el => el.classList.remove("active"));
            div.classList.add("active");
            freqHighlight(entry.value);
        });

        const swatch = document.createElement("span");
        swatch.className = "legend-swatch";
        swatch.style.backgroundColor = toRgba(entry.color);

        const label = document.createElement("span");
        label.className = "legend-label";
        label.textContent = entry.value;

        div.appendChild(swatch);
        div.appendChild(label);
        container.appendChild(div);
    });
}

/* ===== Controls ===== */
function onTextToggle(e) {
    state.showText = e.target.checked;
    redraw();
}

function onItemScaleChange(e) {
    state.itemScale = parseInt(e.target.value);
    document.getElementById("item-scale-value").textContent = state.itemScale + "%";
    redraw();
}

function onPanelScaleChange(e) {
    state.panelScale = parseInt(e.target.value);
    document.getElementById("panel-scale-value").textContent = state.panelScale + "%";
    const s = state.panelScale / 100.0;
    svg.style.transform = `scale(${s})`;
    svg.style.transformOrigin = "0 0";
}

function toggleSidebar() {
    document.getElementById("sidebar").classList.toggle("open");
}

/* ===== Hover Interaction ===== */
let hoverToken = null;

function onSvgHover(e) {
    const row = e.target.closest(".item-row");
    if (!row) return;

    const token = row.dataset.token;
    const vi = parseInt(row.dataset.vi);
    if (token === hoverToken) return;
    hoverToken = token;

    if (state.lockedToken) return;

    const version = state.filteredVersions[vi];
    const li = parseInt(row.dataset.li);
    const item = version ? version.items[li] : null;

    const tokens = getRelatedTokens(token, version, item);
    applyHighlightClasses(svg, state, tokens, "hover-highlighted");
}

function onSvgHoverOut(e) {
    const row = e.target.closest(".item-row");
    if (!row) return;

    const related = e.relatedTarget ? e.relatedTarget.closest(".item-row") : null;
    if (related) return;

    hoverToken = null;
    hideTooltip();

    if (!state.lockedToken) {
        clearHighlightClasses(svg, "hover-highlighted");
    }
}

function onSvgMouseMove(e) {
    const row = e.target.closest(".item-row");
    if (!row) { hideTooltip(); return; }

    const vi = parseInt(row.dataset.vi);
    const li = parseInt(row.dataset.li);
    const version = state.filteredVersions[vi];
    if (!version) return;
    const item = version.items[li];
    if (!item) return;

    showTooltip(e.clientX, e.clientY, item, version);
}

/* ===== Click Interaction ===== */
function onSvgClick(e) {
    const row = e.target.closest(".item-row");

    if (!row) {
        state.lockedToken = null;
        state.lockedVersion = null;
        clearHighlightClasses(svg, "highlighted");
        clearHighlightClasses(svg, "hover-highlighted");
        document.querySelectorAll(".legend-entry.active").forEach(el => el.classList.remove("active"));
        return;
    }

    const token = row.dataset.token;
    const vi = parseInt(row.dataset.vi);
    const li = parseInt(row.dataset.li);
    const version = state.filteredVersions[vi];
    const item = version ? version.items[li] : null;

    if (state.lockedToken === token && state.lockedVersion === vi) {
        state.lockedToken = null;
        state.lockedVersion = null;
        clearHighlightClasses(svg, "highlighted");
        return;
    }

    state.lockedToken = token;
    state.lockedVersion = vi;

    const tokens = getRelatedTokens(token, version, item);
    clearHighlightClasses(svg, "hover-highlighted");
    applyHighlightClasses(svg, state, tokens, "highlighted");
}

function freqHighlight(value) {
    state.lockedToken = "__freq_" + value;
    state.lockedVersion = null;

    const matchedTokens = new Set();
    state.filteredVersions.forEach(v => {
        v.items.forEach(item => {
            if (item.value === value) matchedTokens.add(item.token);
        });
    });

    clearHighlightClasses(svg, "hover-highlighted");
    applyHighlightClasses(svg, state, { matched: matchedTokens }, "highlighted");
}

/* ===== Highlight Helpers ===== */
function getRelatedTokens(token, version, item) {
    const matched = new Set();
    const baseMatched = new Set();

    if (version && version.is_base) {
        baseMatched.add(token);
        const translations = item ? (item.translations || []) : [];
        translations.forEach(t => matched.add(t));
    } else {
        matched.add(token);
        state.filteredVersions.forEach(v => {
            if (v.is_base) {
                v.items.forEach(bItem => {
                    if (bItem.translations && bItem.translations.includes(token)) {
                        baseMatched.add(bItem.token);
                    }
                });
            }
        });
    }

    return { matched, baseMatched };
}

/* ===== Tooltip ===== */
function showTooltip(mx, my, item, version) {
    let html = `<div class="tip-token">${escHtml(item.token)}</div>`;
    html += `<div class="tip-detail">${version.author} (${version.year})</div>`;
    html += `<div class="tip-detail">Frequency: ${item.frequency}`;
    if (item.value !== item.frequency) html += ` &middot; Value: ${item.value}`;
    html += `</div>`;

    if (item.translations && item.translations.length) {
        html += `<div class="tip-trans">DE: ${item.translations.map(escHtml).join(", ")}</div>`;
    }

    tooltip.innerHTML = html;
    tooltip.classList.remove("hidden");

    const pad = 14;
    let left = mx + pad;
    let top = my + pad;
    const rect = tooltip.getBoundingClientRect();
    if (left + rect.width > window.innerWidth - 10) left = mx - rect.width - pad;
    if (top + rect.height > window.innerHeight - 10) top = my - rect.height - pad;

    tooltip.style.left = left + "px";
    tooltip.style.top = top + "px";
}

function hideTooltip() {
    tooltip.classList.add("hidden");
}

function escHtml(str) {
    const div = document.createElement("div");
    div.textContent = str;
    return div.innerHTML;
}

/* ===== Redraw ===== */
function redraw() {
    render(svg, state);

    if (state.lockedToken) {
        if (state.lockedToken.startsWith("__freq_")) {
            const val = parseInt(state.lockedToken.slice(7));
            freqHighlight(val);
        } else {
            const vi = state.lockedVersion || 0;
            const version = state.filteredVersions[vi];
            if (version) {
                const item = version.items.find(it => it.token === state.lockedToken);
                if (item) {
                    const tokens = getRelatedTokens(state.lockedToken, version, item);
                    applyHighlightClasses(svg, state, tokens, "highlighted");
                }
            }
        }
    }
}

document.addEventListener("DOMContentLoaded", init);
