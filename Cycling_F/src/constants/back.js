
// export const LOCAL_HOST = 'http://172.31.249.212:992';
// export const LOCAL_HOST = 'http://localhost:8081';
// export const CUSTOMER_ACCOUNT_API =  LOCAL_HOST+'/Customer';
// export const DEBT_API =  LOCAL_HOST+'/Debt';
// export const BIKE_API =  LOCAL_HOST+'/bike';
// export const Security_API =  LOCAL_HOST+'/Security';

export const CUSTOMER_ACCOUNT_API = 'http://localhost:8083'+'/Customer';
export const DEBT_API = 'http://localhost:8084'+'/Debt';
export const BIKE_API = 'http://localhost:8082'+'/bike';
export const Security_API = 'http://localhost:8085'+'security';

    // export const CUSTOMER_ACCOUNT_API = 'http://customer-service:8083' + '/Customer';
    // export const DEBT_API = 'http://debt-service:8084' + '/Debt';
    // export const BIKE_API = 'http://bike-service:8082' + '/bike';
    // export const Security_API = 'http://security-service:8085' + '/security';


/////////////////////////////// BIKES ///////////////////////////////////
export const GET_STATION = BIKE_API + '/all';
export const UPDATE_STATION = BIKE_API + 'update';
export const INSERT_STATION = BIKE_API + 'add';
export const LOCAL_HOST_STATIONS = BIKE_API+'/station/stations';
export const LOCAL_HOST_BIKESINSTATION = BIKE_API + '/available-in-station';
export const LOCAL_HOST_BIKESID = BIKE_API + '/bike/{id}';
export const LOCAL_HOST_ALLBIKES = BIKE_API+'/bike/all';
export const NUMBEROFBIKES=BIKE_API+'/bike/numberOfBikes';


/////////////////////////////// CUSTOMER ACCOUNT  ///////////////////////////////////

export const LOCAL_HOST_INVOICE = CUSTOMER_ACCOUNT_API + '/invoice/';
export const LOCAL_HOST_INVOICEUNPAID=CUSTOMER_ACCOUNT_API+'/subscription/subscriptionsWithUnpaidInvoices';
export const LOCAL_HOST_INVOICEDETAILS = CUSTOMER_ACCOUNT_API + '/invoiceDetails/';
export const GET_INVOICES = LOCAL_HOST_INVOICE + 'all';
export const GET_INVOICEDETAILS_BY_ID = (invoiceId) =>
    `${LOCAL_HOST_INVOICEDETAILS}details/${invoiceId}`;
export const SEARCH_INVOICES_BY_USERNAME = (username) =>
    `${LOCAL_HOST_INVOICE}searchByUserName?userName=${encodeURIComponent(username)}`;
export const GET_INVOICEDETAILS_BY_DUE_DATE = (dueDate) =>
    `${LOCAL_HOST_INVOICE}details/byDueDate?dueDate=${new Date(dueDate).toISOString()}`;
export const LOCAL_HOST_UNRESOLVED_INVOICES_FOR_SUBSCRIPTION = (subscriptionId) =>
    `${LOCAL_HOST_INVOICE}/unresolvedInvoicesForSubscription/${subscriptionId}`;
export const SEARCH_INVOICES_BY_NAME = (firstName, lastName) =>
    `${LOCAL_HOST_INVOICE}search?firstName=${encodeURIComponent(firstName)}&lastName=${encodeURIComponent(lastName)}`;
export const GET_LAST_NAME_BY_SUBSCRIPTION_ID = (subscriptionId) =>
    `${CUSTOMER_ACCOUNT_API}/customer-account/getLastName/${subscriptionId}`;
export const GET_INVOICES_FOR_SUBSCRIPTION = (subscriptionId) =>
    `${CUSTOMER_ACCOUNT_API}/subscription/InvoicesForSubscription/${subscriptionId}`;
export const GET_FIRST_NAME_BY_INVOICE_ID = (invoiceId) =>
    `${CUSTOMER_ACCOUNT_API}/invoice/getFirstNameIndividualClientByInvoiceId/${invoiceId}`;
export const GET_LAST_NAME_BY_INVOICE_ID = (invoiceId) =>
    `${CUSTOMER_ACCOUNT_API}/invoice/getLastNameIndividualClientByInvoiceId/${invoiceId}`;
export const GET_DUEDATE_INVOICE_ID = (invoiceId) =>
    `${CUSTOMER_ACCOUNT_API}/invoice/getDueDateByInvoiceId/${invoiceId}`;
export const GET_PHONE_BY_INVOICE_ID = (invoiceId) =>
    `${CUSTOMER_ACCOUNT_API}/invoice/getPhoneNumber/${invoiceId}`;
export const GET_EMAIL_BY_INVOICE_ID = (invoiceId) =>
    `${CUSTOMER_ACCOUNT_API}/invoice/getCustomerEmail/${invoiceId}`;
export const GET_ADRESS_BY_INVOICE_ID = (invoiceId) =>
    `${CUSTOMER_ACCOUNT_API}/invoice/getCustomerAddress/${invoiceId}`;
export const GET_PERIOD_OF_INVOICE = (invoiceId) =>
    `${CUSTOMER_ACCOUNT_API}/invoice/getPeriodOfInvoice/${invoiceId}`;
export const GET_DEADLINE_PAIEMENT_OF_INVOICE = (invoiceId) =>
    `${CUSTOMER_ACCOUNT_API}/invoice/getDeadlinePaiementByInvoiceId/${invoiceId}`;
export const GET_ESCOMPTE_OF_SUBSCRIPTION = (subscriptionId) =>
    `${CUSTOMER_ACCOUNT_API}/subscription/calculateTotalUnpaidforSubscription/${subscriptionId}`;
export const GET_SUBSCRIPTION_CODE = (invoiceId) =>
    `${CUSTOMER_ACCOUNT_API}/subscription/getSubscriptionCode/${invoiceId}`;
export const GET_SUBSCRIPTION_COST = (invoiceId) =>
    `${CUSTOMER_ACCOUNT_API}/subscription/getSubscriptionCost/${invoiceId}`;
export const GET_ESCOMPTE_OF_INVOICE = (invoiceId) =>
    `${CUSTOMER_ACCOUNT_API}/subscription/calculateTotalDebtForInvoice/${invoiceId}`;

/////////////////////////////// DEBT ///////////////////////////////////
export const LOCAL_HOST_TEMPLATES=DEBT_API+'/dunningTemplate/allTemplates';
export const LOCAL_HOST_LEVELS=DEBT_API+'/dunningLevel/allLevels';
export const LOCAL_HOST_ACTIONS=DEBT_API+'/dunningAction/allActions';
export const GET_laguage_TEMPLATE_ID = (templateId) =>
        `${DEBT_API}/dunningTemplate/getlanguageBytemplateId/${templateId}`;
export const GET_channel_TEMPLATE_ID = (templateId) =>
        `${DEBT_API}/dunningTemplate/getchannelBytemplateId/${templateId}`;
export const GET_templateName_TEMPLATE_ID = (templateId) =>
        `${DEBT_API}/dunningTemplate/gettemplateNameBytemplateId/${templateId}`;
export const GET_Text_TEMPLATE_ID = (templateId) =>
        `${DEBT_API}/dunningTemplate/getTextBytemplateId/${templateId}`;
export const UPDATE_Text_TEMPLATE_ID = (templateId) =>
        `${DEBT_API}/dunningTemplate/updateText/${templateId}`;
export const GET_LEVEL_TEMPLATE_ID = (templateId) =>
        `${DEBT_API}/dunningTemplate/getLevelNameByTemplateId/${templateId}`;
export const GET_ACTION_TEMPLATE_ID = (templateId) =>
        `${DEBT_API}/dunningTemplate/getActionNameByTemplateId/${templateId}`;


