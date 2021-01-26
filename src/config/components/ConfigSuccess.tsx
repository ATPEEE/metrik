import { Button } from "antd";
import React, { FC } from "react";
import { CheckCircleFilled } from "@ant-design/icons";
import DashboardConfig from "../../components/DashboardConfig";
import { PipelineConfiguration } from "../../clients/apis";

const dataSource: PipelineConfiguration[] = [
	{
		id: "1",
		name: "4km-DEV",
		lastUpdateTime: 1611629036125,
		type: "Jenkins",
		url: "http://18.138.19.85:8001/job/4km-desk-check/",
	},
	{
		id: "2",
		name: "4km-UAT",
		lastUpdateTime: 1611629036125,
		type: "Bamboo",
		url: "http://18.138.19.85:8001/job/4km-desk-check/",
	},

	{
		id: "3",
		name: "4km-PROD",
		lastUpdateTime: 1611629036125,
		type: "Git",
		url: "http://18.138.19.85:8001/job/4km-desk-check/",
	},
];

const ConfigSuccess: FC = () => {
	return (
		<div>
			<div css={{ display: "flex", alignItems: "center", marginBottom: 32 }}>
				<CheckCircleFilled css={{ fontSize: 70, color: "#52C41A" }} />
				<div
					css={{
						display: "flex",
						flexDirection: "column",
						alignItems: "flex-start",
						fontSize: 24,
						color: "black",
						margin: "0 30px",
					}}>
					<span>Pipeline successfully created!</span>
					<span
						css={{
							fontSize: 14,
							opacity: 0.5,
						}}>
						The initial configuration is complete，4 Key Metrics will be presented based on the
						following projects
					</span>
				</div>
				<Button type={"primary"} size={"large"}>
					Go to Dashboard
				</Button>
			</div>
			<DashboardConfig pipelineConfigurations={dataSource} />
		</div>
	);
};

export default ConfigSuccess;
