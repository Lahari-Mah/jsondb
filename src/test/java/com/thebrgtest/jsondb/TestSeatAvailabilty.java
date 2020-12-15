package com.thebrgtest.jsondb;

import com.thebrgtest.jsondb.controller.OffsetBasedPageRequest;
import com.thebrgtest.jsondb.repository.InputSeatProjection;
import com.thebrgtest.jsondb.service.InputService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestSeatAvailabilty {
    private static final Logger log = LoggerFactory.getLogger(JsondbApplication.class);

    @Autowired
    private InputService inputService;

    @Test
    public void TestSeatAvailByProductType(){
        log.info("...Seat Availability Check with Product Id and Type Id....");
        Pageable pageable = new OffsetBasedPageRequest(10, 0);
        List<InputSeatProjection> seatAvailable = new ArrayList<>();
        inputService.findByProductIdAndTypeId(11L,12L,pageable)
                        .forEach(seatAvailable::add);

        Assert.assertNotNull(seatAvailable);
        Assert.assertEquals(seatAvailable.get(0),"[{seatsAvailable:8}]");
    }

    @Test
    public void TestSeatAvailByStartEndDate() throws ParseException {
        log.info("...Seat Availability Check with Time....");
        Pageable pageable = new OffsetBasedPageRequest(10, 0);
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date stDate = dateFormatter.parse("2020-12-11");
        Date edDate = dateFormatter.parse("2020-12-19");

        List<InputSeatProjection> seatAvailable = new ArrayList<>();
        inputService.findByTime(stDate,edDate,pageable)
                .forEach(seatAvailable::add);

        Assert.assertNotNull(seatAvailable);
        MockFields mock = getMock(1);
        Assert.assertEquals(seatAvailable.get(0), mock.field1);
        Assert.assertEquals(seatAvailable.get(1), mock.field2);
    }

    private MockFields getMock(int scenario) {
        switch(scenario) {
            case 1:
                MockFields iMock1 = new MockFields();
                iMock1.field1="{\"seatsAvailable\":8}";
                iMock1.field2="{\"seatsAvailable\":6}";
                return iMock1;
            case 2:
                MockFields iMock2 = new MockFields();
                return iMock2;
            default:
                return null;
        }
    }

    @Test
    public void TestSeatAvailByAll(){
        log.info("...Seat Availability Check with All params....");
        Date stDate = new Date(2020-12-11);
        Date edDate = new Date(2020-12-19);
        List<InputSeatProjection> seatAvailable= inputService.findByAllParams(214L,218L,stDate,edDate,null);

        Assert.assertNotNull(seatAvailable);
        Assert.assertEquals(seatAvailable,"2");
    }
}
