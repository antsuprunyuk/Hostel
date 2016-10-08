package by.suprunyuk.hostel.command;

public enum CommandEnum {
	LOGIN {
		{
			this.command = new LoginCommand();
		}
	},
	LOGOUT {
		{
			this.command = new LogoutCommand();
		}
	},
	SWITCH_LANGUAGE {
		{
			this.command = new SwitchLanguageCommand();
		}
	},
	CRASH_RECOVERY {
		{
			this.command = new CrashRecoveryCommand();
		}
	},
	REGISTER {
		{
			this.command = new RegisterClientCommand();
		}
	},
	DEMAND {
		{
			this.command = new DemandCommand(); 
		}
	},
	BOOKING {
		{
			this.command = new BookingCommand();
		}
	},
	PAYMENT {
		{
			this.command = new PaymentCommand();
		}
	},
	REFUSE_DEMAND {
		{
			this.command = new RefuseDemandCommand();
		}
	},
	REFUSE_PAYMENT {
		{
			this.command = new RefusePaymentCommand();
		}
	},
	PAYMENT_DETAILS {
		{
			this.command = new PaymentDetailsCommand();
		}
	},
	BOOKING_RESULT {
		{
			this.command = new BookingResultCommand();
		}
	},
	REJECT_DEMAND {
		{
			this.command = new RejectDemandCommand();
		}
	},
	DEMANDS_MANAGE {
		{
			this.command = new DemandsManageCommand();
		}
	},
	BONUS_BANS {
		{
			this.command = new BonusBansCommand();
		}
	},
	APPROVE_DEMAND {
		{
			this.command = new ApproveDemandCommand();
		}
	},
	DISAPPROVE_DEMAND {
		{
			this.command = new DisapproveDemandCommand();
		}
	}, 
	SET_DISCOUNT {
		{
			this.command = new SetDiscountCommand();
		}
	},
	UNBAN_CLIENT {
		{
			this.command = new UnbanClientCommand();
		}
	},
	BAN_CLIENT {
		{
			this.command = new BanClientCommand();
		}
	},
	LOGIN_PAGE_JUMP {
		{
			this.command = new LoginPageJumpCommand();
		}
	},
	MAIN_PAGE_JUMP {
		{
			this.command = new MainPageJumpCommand();
		}
	},
	PRICES_PAGE_JUMP {
		{
			this.command = new PricesPageJumpCommand();
		}
	},
	APARTMENTS_PAGE_JUMP {
		{
			this.command = new ApartmentsPageJumpCommand();
		}
	},
	COORDINATES_PAGE_JUMP {
		{
			this.command = new CoordinatesPageJumpCommand();
		}
	},
	REGISTRATION_PAGE_JUMP {
		{
			this.command = new RegistrationJumpCommand();
		}
	}
	;

	ActionCommand command;
	public ActionCommand getCurrentCommand() {
		return command;
	}

}
