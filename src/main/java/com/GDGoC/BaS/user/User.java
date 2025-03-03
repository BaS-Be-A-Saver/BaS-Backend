package com.GDGoC.BaS.user;

import static com.GDGoC.BaS.user.enums.Provider.GOOGLE;
import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.GDGoC.BaS.booth.BoothUser;
import com.GDGoC.BaS.clothing.UserAccessory;
import com.GDGoC.BaS.clothing.UserHead;
import com.GDGoC.BaS.clothing.UserTowel;
import com.GDGoC.BaS.notification.Notification;
import com.GDGoC.BaS.shower.UserRecord;
import com.GDGoC.BaS.user.enums.Eye;
import com.GDGoC.BaS.user.enums.Mouth;
import com.GDGoC.BaS.user.enums.Nose;
import com.GDGoC.BaS.user.enums.Provider;
import com.GDGoC.BaS.user.enums.Skin;
import com.GDGoC.BaS.waterdrop.WaterdropHistory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 600")
    @Min(1)
    @Max(1200)
    private Integer goal;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    @Min(0)
    private Integer waterdrop;

    @Enumerated(STRING)
    @Column(nullable = false, length = 20, columnDefinition = "VARCHAR(20) DEFAULT 'LIGHT'")
    private Skin skin;

    @Enumerated(STRING)
    @Column(nullable = false, length = 20)
    private Eye eye;

    @Enumerated(STRING)
    @Column(nullable = false, length = 20)
    private Nose nose;

    @Enumerated(STRING)
    @Column(nullable = false, length = 20)
    private Mouth mouth;

    @Enumerated(STRING)
    @Column(nullable = false)
    private Provider provider;

    @Column(nullable = false)
    private String providerId;

    @OneToMany(mappedBy = "user", cascade = ALL, orphanRemoval = true)
    private List<WaterdropHistory> waterdropHistories = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = ALL, orphanRemoval = true)
    private List<UserRecord> userRecords = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = ALL, orphanRemoval = true)
    private List<Notification> notifications = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = ALL, orphanRemoval = true)
    private List<BoothUser> boothUsers = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = ALL, orphanRemoval = true)
    private List<UserHead> userHeads = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = ALL, orphanRemoval = true)
    private List<UserTowel> userTowels = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = ALL, orphanRemoval = true)
    private List<UserAccessory> userAccessories = new ArrayList<>();

    private User(String email, String nickname, Provider provider, String providerId) {
        this.email = email;
        this.nickname = nickname;
        this.goal = 600;
        this.waterdrop = 0;
        this.skin = Skin.LIGHT;
        this.eye = Eye.BASIC;
        this.nose = Nose.BASIC;
        this.mouth = Mouth.BASIC;
        this.provider = provider;
        this.providerId = providerId;
    }

    public static User createByGoogleOAuth(String email, String providerId) {
        String nickname = "google_" + email.substring(0, email.indexOf("@"));
        return new User(email, nickname, GOOGLE, providerId);
    }
}
