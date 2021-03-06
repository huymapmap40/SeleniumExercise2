package test_cases.UI;

import org.testng.annotations.Test;
import page_objects.Z_demo_page.GoBearPage;
import page_objects.Z_demo_page.InsuranceResultsPage;
import test_cases.TestSetup;
import utilities.Constant;
import test_data.general.PageURLs;

import static org.testng.Assert.assertEquals;

public class GoBearTest extends TestSetup {

    private String insurerName = "FPG Insurance";
    private String sortType = "Price: Low to high";
    private String destinationName = "Vietnam";
    private int startDate = 30;

    @Test
    public void TravelInsuranceTest() {
        System.out.println("Demo test travel insurance");

        // Go to https://www.gobear.com/ph?x_session_type=UAT
        GoBearTest.setupTest(PageURLs.GOBEAR_PAGE);
        GoBearPage mainGoBearPage = GoBearPage.getInstance();

        mainGoBearPage.chooseInsuranceTab();
        mainGoBearPage.chooseTravelTab();

        //Go to the Travel results page
        InsuranceResultsPage insuranceResultsPage = mainGoBearPage.showMyResults();

        //Check at least 3 cards are being displayed
        boolean isThreeCardDisplay = insuranceResultsPage.isItemResultDisplayed();

        // VP: Make sure at least 3 cards are being displayed
        assertEquals(isThreeCardDisplay, true);

        // VP: Is Filter label display
        assertEquals(insuranceResultsPage.isFilterSectionDisplayed(), true);

        //From Filter section choose FPG Insurance
        insuranceResultsPage.chooseInsurer(insurerName);

        // Select least 1 option for 1 Section
        insuranceResultsPage.selectSortOption(sortType);
        insuranceResultsPage.selectDestination(destinationName);
        insuranceResultsPage.selectDate(startDate);

        // VP: Message is matched with input message
//        assertEquals(insuranceResultsPage.getExistCardBrandName(insurerName),insurerName);

        GoBearTest.cleanUpTest();
    }
}
