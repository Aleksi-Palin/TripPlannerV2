// Lisää automaattisen täydennyksen kaikille autocomplete-kentille.
document.querySelectorAll(".autocomplete").forEach((autocomplete) => {
    // Etsii kentän ja siihen liittyvät vaihtoehdot.
    const input = autocomplete.querySelector("input");
    const options = [...autocomplete.querySelectorAll('[role="option"]')];
    // Tallentaa näppäimistöllä valitun vaihtoehdon indeksin.
    let activeIndex = -1;

    // Sulkee vaihtoehtolistan ja tyhjentää valinnan.
    const closeOptions = () => {
        autocomplete.classList.remove("is-open");
        input.setAttribute("aria-expanded", "false");
        activeIndex = -1;
        options.forEach((option) => option.removeAttribute("aria-selected"));
    };

    // Suodattaa vaihtoehdot käyttäjän kirjoittaman tekstin perusteella.
    const updateOptions = () => {
        const query = input.value.trim().toLowerCase();
        let visibleCount = 0;

        // Näyttää vain vaihtoehdot, jotka vastaavat hakutekstiä.
        options.forEach((option) => {
            const matches = !query || option.textContent.toLowerCase().includes(query);
            option.hidden = !matches;
            if (matches) {
                visibleCount += 1;
            }
        });

        // Avaa listan, jos vähintään yksi vaihtoehto sopii hakuun.
        if (visibleCount > 0) {
            autocomplete.classList.add("is-open");
            input.setAttribute("aria-expanded", "true");
        } else {
            closeOptions();
        }
    };

    // Siirtää valitun vaihtoehdon syöttökenttään.
    const selectOption = (option) => {
        input.value = option.textContent;
        closeOptions();
        input.focus();
    };

    // Päivittää vaihtoehdot kirjoittamisen ja kentän avaamisen jälkeen.
    input.addEventListener("input", updateOptions);
    input.addEventListener("focus", updateOptions);

    // Mahdollistaa vaihtoehtojen selaamisen näppäimistöllä.
    input.addEventListener("keydown", (event) => {
        const visibleOptions = options.filter((option) => !option.hidden);
        if (!autocomplete.classList.contains("is-open") || visibleOptions.length === 0) {
            return;
        }

        // Nuolinäppäimet vaihtavat aktiivista vaihtoehtoa.
        if (event.key === "ArrowDown" || event.key === "ArrowUp") {
            event.preventDefault();
            activeIndex = (activeIndex + (event.key === "ArrowDown" ? 1 : -1)
                + visibleOptions.length) % visibleOptions.length;
            visibleOptions.forEach((option, index) => {
                const active = index === activeIndex;
                option.classList.toggle("is-active", active);
                option.setAttribute("aria-selected", active);
            });
        // Enter valitsee aktiivisen vaihtoehdon.
        } else if (event.key === "Enter" && activeIndex >= 0) {
            event.preventDefault();
            selectOption(visibleOptions[activeIndex]);
        // Escape sulkee vaihtoehtolistan.
        } else if (event.key === "Escape") {
            closeOptions();
        }
    });

    // Mahdollistaa vaihtoehdon valitsemisen hiirellä.
    options.forEach((option) => {
        option.addEventListener("mousedown", (event) => {
            event.preventDefault();
            selectOption(option);
        });
    });
});

// Sulkee avoimet vaihtoehtolistat, kun käyttäjä klikkaa niiden ulkopuolelle.
document.addEventListener("click", (event) => {
    document.querySelectorAll(".autocomplete").forEach((autocomplete) => {
        if (!autocomplete.contains(event.target)) {
            autocomplete.classList.remove("is-open");
            autocomplete.querySelector("input").setAttribute("aria-expanded", "false");
        }
    });
});
