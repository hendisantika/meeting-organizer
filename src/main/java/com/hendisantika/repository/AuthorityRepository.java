package com.hendisantika.repository;

import com.hendisantika.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 14/02/22
 * Time: 08.18
 * To change this template use File | Settings | File Templates.
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Authority findByAuthority(String authority);
}