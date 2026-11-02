document.querySelectorAll(".autocomplete").forEach((autocomplete) => {
    const input = autocomplete.querySelector("input");
    const options = [...autocomplete.querySelectorAll('[role="option"]')];
    let activeIndex = -1;

    const closeOptions = () => {
        autocomplete.classList.remove("is-open");
        input.setAttribute("aria-expanded", "false");
        activeIndex = -1;
        options.forEach((option) => option.removeAttribute("aria-selected"));
    };

    const updateOptions = () => {
        const query = input.value.trim().toLowerCase();
        let visibleCount = 0;

        options.forEach((option) => {
            const matches = !query || option.textContent.toLowerCase().includes(query);
            option.hidden = !matches;
            if (matches) {
                visibleCount += 1;
            }
        });

        if (visibleCount > 0) {
            autocomplete.classList.add("is-open");
            input.setAttribute("aria-expanded", "true");
        } else {
            closeOptions();
        }
    };

    const selectOption = (option) => {
        input.value = option.textContent;
        closeOptions();
        input.focus();
    };

    input.addEventListener("input", updateOptions);
    input.addEventListener("focus", updateOptions);

    input.addEventListener("keydown", (event) => {
        const visibleOptions = options.filter((option) => !option.hidden);
        if (!autocomplete.classList.contains("is-open") || visibleOptions.length === 0) {
            return;
        }

        if (event.key === "ArrowDown" || event.key === "ArrowUp") {
            event.preventDefault();
            activeIndex = (activeIndex + (event.key === "ArrowDown" ? 1 : -1)
                + visibleOptions.length) % visibleOptions.length;
            visibleOptions.forEach((option, index) => {
                const active = index === activeIndex;
                option.classList.toggle("is-active", active);
                option.setAttribute("aria-selected", active);
            });
        } else if (event.key === "Enter" && activeIndex >= 0) {
            event.preventDefault();
            selectOption(visibleOptions[activeIndex]);
        } else if (event.key === "Escape") {
            closeOptions();
        }
    });

    options.forEach((option) => {
        option.addEventListener("mousedown", (event) => {
            event.preventDefault();
            selectOption(option);
        });
    });
});

document.addEventListener("click", (event) => {
    document.querySelectorAll(".autocomplete").forEach((autocomplete) => {
        if (!autocomplete.contains(event.target)) {
            autocomplete.classList.remove("is-open");
            autocomplete.querySelector("input").setAttribute("aria-expanded", "false");
        }
    });
});
