package com.hendisantika.repository;

import com.hendisantika.domain.Meeting;
import com.hendisantika.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 10/11/25
 * Time: 07:00
 * To change this template use File | Settings | File Templates.
 */
public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    List<Meeting> findByUsersContaining(User user);
}
