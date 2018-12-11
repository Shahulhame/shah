<?xml version="1.0" encoding="UTF-8"?>
<InvoiceTransmission xsi:schemaLocation="http://www.IATA.com/IATAAviationInvoiceStandard http://www.iata.org/services/finance/sis/Documents/schemas/IATA_IS_XML_Invoice_Standard_V3.9.0.1.xsd" xmlns="http://www.IATA.com/IATAAviationInvoiceStandard" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
  <TransmissionHeader>
    <TransmissionDateTime>2018-08-16T04:18:45</TransmissionDateTime>
    <Version>IATA:ISXMLInvoiceV3.9.0.1</Version>
    <TransmissionID>793b3df6-61f4-44bb-bcfc-4a010865ef71</TransmissionID>
    <ReceivingOrganizationID>A89</ReceivingOrganizationID>
    <BillingCategory>Miscellaneous</BillingCategory>
  </TransmissionHeader>
  <Invoice>
    <InvoiceHeader>
      <InvoiceNumber>SC00078353</InvoiceNumber>
      <InvoiceDate>2018-08-13</InvoiceDate>
      <InvoiceType>Invoice</InvoiceType>
      <ChargeCategory>IT Services</ChargeCategory>
      <SellerOrganization>
        <OrganizationID>950</OrganizationID>
        <OrganizationDesignator>XS</OrganizationDesignator>
        <OrganizationName1>SITA</OrganizationName1>
        <TaxRegistrationID>RPM Bruxelles</TaxRegistrationID>
        <CompanyRegistrationID>BE 0403 150 410</CompanyRegistrationID>
        <Address>
          <AddressLine1>Cooperative society</AddressLine1>
          <AddressLine2>Registered Office</AddressLine2>
          <AddressLine3>Avenue des Olympiades 2</AddressLine3>
          <CityName>BRUXELLES</CityName>
          <SubdivisionName>null</SubdivisionName>
          <CountryCode>BE</CountryCode>
          <CountryName>BELGIUM</CountryName>
          <PostalCode>1140</PostalCode>
        </Address>
      </SellerOrganization>
      <BuyerOrganization>
        <OrganizationID>A89</OrganizationID>
        <OrganizationDesignator>XB</OrganizationDesignator>
        <OrganizationName1>IATA MONTREAL</OrganizationName1>
        <TaxRegistrationID>107510570RT0001</TaxRegistrationID>
        <AdditionalTaxRegistrationID>1006066123</AdditionalTaxRegistrationID>
        <Address>
          <AddressLine1>International Air Transport Association</AddressLine1>
          <AddressLine2>800 Place Victoria, PO Box 113, 14th Floor</AddressLine2>
          <CityName>MONTREAL</CityName>
          <SubdivisionName>Quebec</SubdivisionName>
          <CountryCode>CA</CountryCode>
          <CountryName>CANADA</CountryName>
          <PostalCode>H4Z 1M1</PostalCode>
        </Address>
      </BuyerOrganization>
      <PaymentTerms>
        <CurrencyCode>USD</CurrencyCode>
        <ClearanceCurrencyCode>USD</ClearanceCurrencyCode>
        <ExchangeRate>1.00000</ExchangeRate>
        <SettlementMonthPeriod>180801</SettlementMonthPeriod>
        <SettlementMethod>I</SettlementMethod>
      </PaymentTerms>
      <ISDetails>
        <DigitalSignatureFlag>D</DigitalSignatureFlag>
      </ISDetails>
      <Notes Type="Affiliate">XB/0000000691</Notes>
      <Attachment>
        <AttachmentIndicatorOriginal>N</AttachmentIndicatorOriginal>
        <AttachmentIndicatorValidated>N</AttachmentIndicatorValidated>
        <NumberOfAttachments>0</NumberOfAttachments>
      </Attachment>
    </InvoiceHeader>
    <LineItem>
      <LineItemNumber>1</LineItemNumber>
      <ChargeCode>Communication Charges</ChargeCode>
      <Description>Managed Security Appliance</Description>
      <ProductID>50038</ProductID>
      <StartDate>2018-07-01</StartDate>
      <EndDate>2018-07-31</EndDate>
      <LocationCode>GVA</LocationCode>
      <Quantity UOMCode="EA">1</Quantity>
      <UnitPrice SF="1">2637.0000</UnitPrice>
      <ChargeAmount>2637.000</ChargeAmount>
      <TotalNetAmount>2637.000</TotalNetAmount>
    </LineItem>
    <LineItemDetail>
      <DetailNumber>1</DetailNumber>
      <LineItemNumber>1</LineItemNumber>
      <Description>Managed Security Appliance</Description>
      <ProductID>50038</ProductID>
      <StartDate>2018-07-01</StartDate>
      <EndDate>2018-07-31</EndDate>
      <Quantity UOMCode="EA">1</Quantity>
      <UnitPrice SF="1">2637.0000</UnitPrice>
      <ChargeAmount>2637.000</ChargeAmount>
      <TotalNetAmount>2637.000</TotalNetAmount>
      <RouteDetails>
        <CountryCode>CH</CountryCode>
      </RouteDetails>
      <ServiceProviderDetails>
        <MemberCode>XB</MemberCode>
        <NumericCustomerCode>0000000691</NumericCustomerCode>
        <ProductGroup>Internet and Security Services</ProductGroup>
        <ProductName>MSA</ProductName>
        <ActivityDate>2018-07-01</ActivityDate>
        <StationCode>GVA</StationCode>
      </ServiceProviderDetails>
    </LineItemDetail>
    <InvoiceSummary>
      <LineItemCount>1</LineItemCount>
      <TotalLineItemAmount>2637.000</TotalLineItemAmount>
      <TotalTaxAmount>0.000</TotalTaxAmount>
      <TotalVATAmount>0.000</TotalVATAmount>
      <TotalAmount>2637.000</TotalAmount>
      <TotalAmountInClearanceCurrency>2637.000</TotalAmountInClearanceCurrency>
    </InvoiceSummary>
  </Invoice>
  <TransmissionSummary>
    <InvoiceCount>1</InvoiceCount>
    <TotalAmount CurrencyCode="USD">2637.000</TotalAmount>
    <TotalTaxAmount CurrencyCode="USD">0.000</TotalTaxAmount>
    <TotalVATAmount CurrencyCode="USD">0.000</TotalVATAmount>
  </TransmissionSummary>
</InvoiceTransmission>
