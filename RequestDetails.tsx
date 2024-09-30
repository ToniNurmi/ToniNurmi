const location = useLocation();

    useEffect(() => {
        //Finds the issue number and license plate from the URL and fills the input fields with them, then submits automatically
        const queryParams = new URLSearchParams(location.search);
        const issueNumberParam = queryParams.get('i');
        const licensePlateParam = queryParams.get('r');

        if (issueNumberParam && licensePlateParam) {
            setIssueNumber(issueNumberParam)
            setLicensePlate(licensePlateParam)
            fetchTicketInfo();
        }
    }, [location]);

    const handleChange = (event: ChangeEvent<HTMLInputElement>) => {
        event.preventDefault();
        switch (event.currentTarget.id) {
            case "floatingIssueNumberInput":
                setIssueNumber(event.currentTarget.value)
                break
            case "floatingLicensePlate":
                setLicensePlate(event.currentTarget.value)
                break
        }
    };

    const fetchTicketInfo = async (event?: React.MouseEvent<HTMLButtonElement>) => {
        if (event) event?.preventDefault();
        getTicketTrigger([issueNumber, licensePlate])
    }
