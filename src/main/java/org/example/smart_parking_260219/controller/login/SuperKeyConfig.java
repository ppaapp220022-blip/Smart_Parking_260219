package org.example.smart_parking_260219.controller.login;

public class SuperKeyConfig {
    /*
    포트폴리오 시연용 -> super 계정
    - super 계정은 데이터베이스에 등록되어있는 ID, PW, email 인증 통과 시 SUPER_OTP를 통해 즉시 로그인 가능
    - super(role) : admin(관리자 메뉴 토글) + normal(내 정보 수정) = 동시 표시
     */

    /*
    SUPER_OTP
    - 실제 발송된 OTP와 달라도 입력값이 SUPER_OTP이면 인증 통과
    - 모든 계정에도 적용
     */

    private SuperKeyConfig() {}

    /* 슈퍼 계정 아이디 */
    public static final String SUPER_ID = getOrDefault("SMART_PARKING_SUPER_ID", "super");

    /* 슈퍼패스 OTP */
    // 기본값은 빈 값으로 두어, 환경변수를 명시적으로 넣기 전에는 우회 인증이 비활성화됩니다.
    public static final String SUPER_OTP = getOrDefault("SMART_PARKING_SUPER_OTP", "");
    public static final boolean SUPER_OTP_ENABLED =
            Boolean.parseBoolean(getOrDefault("SMART_PARKING_SUPER_OTP_ENABLED", "false"));

    /* 세션에 저장할 슈퍼 역할값 (menu.jsp 분기 기준) */
    public static final String SUPER_ROLE = "SUPER";

    /* 슈퍼 계정 여부 확인 */
    public static boolean isSuperAccount(String managerId) {
        return SUPER_ID.equals(managerId);
    }

    /* 슈퍼패스 OTP 여부 확인 */
    public static boolean isSuperOtp(String inputOtp) {
        return SUPER_OTP_ENABLED && !SUPER_OTP.isBlank() && SUPER_OTP.equals(inputOtp);
    }

    private static String getOrDefault(String key, String defaultValue) {
        String value = System.getenv(key);
        return (value == null || value.isBlank()) ? defaultValue : value;
    }
}
